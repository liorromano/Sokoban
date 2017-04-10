package entities;

import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


 public class SokobanDBManager extends Observable implements DBmanager{
	 private static SessionFactory factory;
	 private Configuration configuration;

	 public SokobanDBManager() {
		 Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}

	 public void addLevel(String fName) {
			LevelsManager level = new LevelsManager(fName);
			Transaction tx = null;
			Session session = factory.openSession();
			try {
				tx = session.beginTransaction();
				session.save(level);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}

		}

		public void addUser(String fName) {
			UsersManager user = new UsersManager(fName);
			Transaction tx = null;
			Session session = factory.openSession();
			try {
				tx = session.beginTransaction();
				session.save(user);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

		private void addScore(String name, String levelName,int time, int steps) {
			ScoresManager emp = new ScoresManager(name,levelName,time,steps);
			Transaction tx = null;
			Session session = factory.openSession();
			try {
			tx = session.beginTransaction();
			session.save(emp);
			tx.commit();
			}
			catch (HibernateException e) {
			if (tx != null)
			tx.rollback();
			e.printStackTrace();
			} finally {
			session.close();
			}
		}
		private void deleteScore(ScoresManager score) {
			Session session = factory.openSession();
			Transaction tx = null;
			try {
			tx = session.beginTransaction();
			session.delete(score);
			tx.commit();
			}
			catch (HibernateException e) {
			if (tx != null)
			tx.rollback();
			e.printStackTrace();
			}
			finally {
			session.close();
			}
			}

		public void checkIfsave(String name,String levelName, int step, int time) {
			UsersManager user=null;
			ScoresManager scores=null;
			LevelsManager levels=null;

			Session session = factory.openSession();

			Query<UsersManager> query = session.createQuery("from Users E where E.fullname='"+name+"'");
			List<UsersManager> list = query.list();
			if(!list.isEmpty())
			{
				user=list.get(0);
			}
			else
			{
				addUser(name);
			}

			Query<LevelsManager> query2 = session.createQuery("from Levels E where E.levelname='"+levelName+"'");
			List<LevelsManager> list2 = query2.list();
			if (!list2.isEmpty())
			{
				levels=list2.get(0);
			}
			else
			{
				addLevel(levelName);
			}

			Query<ScoresManager> query3 = session.createQuery("from Scores s Where s.levelname='"+levelName+"' and s.fullname='"+name+"'");
			List<ScoresManager> list3 = query3.list();

			if (!list3.isEmpty())
			{
				scores=list3.get(0);
				if(scores.getSteps()>step||scores.getTime()>time)
				{
					deleteScore(scores);
					addScore(name,levelName, time, step);
				}

			}
			else
			{
				addScore(name, levelName, time, step);
			}

		}

 }
