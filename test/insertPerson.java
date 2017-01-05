
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//测试类
public class insertPerson {
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
	public void insertPerson() {
		// 生成person对象
		Person person = new Person(14, "gaohan", "222", "girl", "qq.com");
		System.out.println("person id" + person.getId());
		// 保存对象
		session.save(person);
		System.out.println("person1:" + person.toString());
		
		
		
		Query query = session.createQuery("from Person");
		System.out.println("print query：" + query.toString());
		java.util.List list = query.list();
		System.out.println("print list:" + list.toString());
		java.util.Iterator its = ((Collection) list).iterator();
		System.out.println("print its:" + its.toString());
		
		while (its.hasNext()) {
			System.out.println("表明数据库里有数据");
			Person person2 = (Person) its.next();
			System.out.println("print person2" + person2.toString());
			// 取出id
			System.out.println("打印取出的peron的id：" + person2.getId() + "打印输出的name: " + person2.getName() );
		}
	}

}
