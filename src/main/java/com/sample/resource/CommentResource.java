package com.sample.resource;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sample.comment;
import com.sample.dao.CommentDao;
import com.sample.exec.Executor;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/comment")
public class CommentResource {

	@GET
	@Produces("application/json")
	public List<comment> getCommentAll() {

		try (SqlSession session = Executor.initquery().openSession();) {
			CommentDao dao = session.getMapper(CommentDao.class);
			List<comment> list = dao.selectAll();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@GET
	@Path("{topicid}")
	@Produces("application/json")
	public List<comment> getCommentList(@PathParam("topicid") int topicid) {

		try (SqlSession session = Executor.initquery().openSession();) {
			CommentDao dao = session.getMapper(CommentDao.class);
			return dao.findSelect(topicid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Consumes("application/json")
	public Response saveComment(comment com) {
		try (SqlSession session = Executor.initquery().openSession();) {
			CommentDao dao = session.getMapper(CommentDao.class);
			com.setDate(new Date());
			System.out.println(com);
			dao.insert(com);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("{comment_id}")
	@Consumes("application/json")
	public Response remove(@QueryParam("comment_id") int id) {
		try (SqlSession session = Executor.initquery().openSession();) {
			CommentDao dao = session.getMapper(CommentDao.class);
			System.out.println(id);
			dao.delete(id);
			session.commit();
			System.out.println("comment-deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
	}

}
