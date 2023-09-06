package com.sample.resource;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sample.topic;
import com.sample.dao.TopicDao;
import com.sample.exec.Executor;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("topic")
public class TopicResource {

	@GET
	@Produces("application/json")
	public List<topic> getSelectAll() {

		try (SqlSession session = Executor.initquery().openSession();) {
			TopicDao dao = session.getMapper(TopicDao.class);
			List<topic> list = dao.selectAll();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("{topicid}")
	@Produces("application/json")
	public topic getTopicById(@PathParam("topicid") int topicid) {

		try (SqlSession session = Executor.initquery().openSession();) {
			TopicDao dao = session.getMapper(TopicDao.class);
			return dao.findByPrimarykey(topicid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Produces("application/json")
	public Response save(topic topic) {

		try (SqlSession session = Executor.initquery().openSession();) {
			TopicDao dao = session.getMapper(TopicDao.class);
			dao.insert(topic);
			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}
}
