import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.AliasToBeanResultTransformer;


public class PersonController {

	private Session session = null;

	public static void main(String[] args) {
		new PersonController().init();
		new PersonController().savePerson();
		new PersonController().destroy();
	}

	// 开启session，开启事务
	public void init() {
		System.out.println("session start!");
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 建立SessionFactory
		SessionFactory factory = config.buildSessionFactory();
		// 顺序1--开启会话
		session = factory.openSession();
		// 顺序2--开启事务
		session.beginTransaction();
	}

	// 关闭事务，关闭session
	public void destroy() {
		System.out.println("session close!");
		// 顺序3--(关闭)提交事务
		session.getTransaction().commit();
		// 顺序4--关闭会话
		session.close();
	}

	// 插入数据
	public void savePerson() {
		System.out.println(" start saving!");
		// 生成person对象
		// Person person = new Person(2, "gaoziqiang", "111", "boy", "163.com");
		// 保存对象
		// session.save(person);
		// session.flush();
		// session.beginTransaction().commit();
		//for (int i = 0; i < 5; i++) {
			Person person = new Person(3, "gaohanya", "00", "boy", "22.com");
			session.save(person);
			//if (i == 4) {
				session.flush();
				//session.clear();
			//}
		//}
		System.out.println("end saving!");
	}

	// 查询数据
	public void search() {
		// 生成person对象
		// Person person2 = new Person(14, "gaohan", "222", "girl", "qq.com");
		Query query = session.createQuery("select id, name from person");
		query.setResultTransformer(new AliasToBeanResultTransformer(Person.class));
		System.out.println(query.toString());
		java.util.List list = query.list();
		System.out.println(list.toString());
		// userList = (List) query.list();
		java.util.Iterator its = ((Collection) list).iterator();

		while (its.hasNext()) {
			Person person = (Person) its.next();
			System.out.println("print person:" + person);
			// 取出id
			System.out.println(person.getId());
		}
	}

}