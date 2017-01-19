
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
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
		//System.out.println("person id" + person.getId());
		// 保存对象
		session.save(person);
		//System.out.println("person1:" + person.toString());

		// 数据查询
		Query query = session.createSQLQuery("select name from person");
		//System.out.println("print query：" + query.toString());
		
		List<Map<String,Object>> list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		System.out.println(list.toString());
		for(Object obj:list) {
			Map mapPerson = (Map) obj;
			mapPerson.get("name");
			System.out.println("--print name ---" + mapPerson.get("name"));
		}
		
		
		/*query.getReturnAliases();
		System.out.println("query's Aliases:" + query.getReturnAliases());
		List persons = query.getResultList();
		for(int i = 0;i < persons.size();i ++) {
			System.out.println("---print---" + persons.get(i));
		}
		java.util.List list = query.list();
		System.out.println("print list:" + list.toString());
		java.util.Iterator its = ((Collection) list).iterator();
		System.out.println("print its:" + its.toString());

		while (its.hasNext()) {
			System.out.println("数据库里有数据");
			int personId = (int) its.next();
			System.out.println("print person2" + personId);
			// 取出id
			System.out.println("打印取出的peron的id：" + personId);
		}*/
	}

}
