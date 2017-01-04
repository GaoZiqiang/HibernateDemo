
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import antlr.collections.List;

//测试类
public class Querya {
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

	/*@Test
	public void testSavePerson() {
		System.out.println("save!");
		// 生成person对象
		//Person person = new Person(2, "gaoziqiang", "111", "boy", "163.com");
		//Person person2 = new Person(2, "gaohan", "222", "girl", "qq.com");
		// 保存对象
		//session.save(person);
		//session.beginTransaction().commit();
		for(int i = 0;i < 5;i ++) {
			Person person = new Person(i,"gaohanya","00","boy","22.com");
			session.save(person);
			if(i == 4) {
				session.flush();
				session.clear();
			}
		}
		
		//session.getTransaction().commit();
	}*/
	
	@Test
	public void testSavePerson2() {
		// 生成person对象
		Person person2 = new Person(14, "gaohan", "222", "girl", "qq.com");
		// 保存对象
		Query query = session.createQuery("from Person");
		System.out.println(query.toString());
		java.util.List list = query.list();
		System.out.println(list.toString());
		// userList = (List) query.list();
		java.util.Iterator its = ((Collection) list).iterator();
		// System.out.println(ls);
		// Iterator it = (Iterator) ((ArrayList) ls).iterator();
		// while() {
		// System.out.println();
		// }
		while (its.hasNext()) {
			Person person = (Person) its.next();
			//取出id
			System.out.println(person.getId());
		}
		//session.getTransaction().commit();
		//session.close();
	}

	/*@Test
	public void testGetPerson() {
		// System.out.println("HelloWorld!");
		// 从数据库获取数据，方法一
		// List ls = (List) new ArrayList();
		// System.out.println(ls);
		
		String selectHql = "from Person";
		System.out.println(selectHql);
		Query query = session.createQuery(selectHql);
		//System.out.println(query);
		java.util.List list = query.list();
		System.out.println(list.toString());
		// userList = (List) query.list();
		java.util.Iterator its = ((Collection) list).iterator();
		// System.out.println(ls);
		// Iterator it = (Iterator) ((ArrayList) ls).iterator();
		// while() {
		// System.out.println();
		// }
		while (its.hasNext()) {
			Person person = (Person) its.next();
			//取出id
			System.out.println(person.getId());
		}
	}*/
}
