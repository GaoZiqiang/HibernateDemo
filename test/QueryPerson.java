
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//测试类
public class QueryPerson {
	private Session session = null;

	@Before
	public void init() {
		System.out.println("start!");
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 建立SessionFactory
		SessionFactory factory = config.buildSessionFactory();
		// 顺序1--开启会话
		session = factory.openSession();
		// 顺序2--开启事务
		session.beginTransaction();

	}

	@After
	public void destory() {
		System.out.println("end!");
		// 顺序3--(关闭)提交事务
		session.getTransaction().commit();
		// 顺序4--关闭会话
		session.close();

	}

	@Test
	public void searchPerson() {
		Query query = session.createQuery("SELECT person FROM Person person WHERE person.id = :id");
		System.out.println("print query：" + query.toString());
		java.util.List list = query.list();
		System.out.println("print list:" + list.toString());
		java.util.Iterator its = ((Collection) list).iterator();
		System.out.println("print its:" + its.toString());
		
		while (its.hasNext()) {
			System.out.println("表明数据库里有数据");
			Person person = (Person) its.next();
			System.out.println("print person" + person);
			// 取出id
			System.out.println(person.getId());
		}

	}

}
