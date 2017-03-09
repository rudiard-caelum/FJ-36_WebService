package br.com.caelum.jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("jms")
public class JMSResource {

	@Inject
	private JMSContext ctx;
	@Resource(name = "java:/jboss/exported/jms/TESTE.TOPICO")
	private Destination topico;

	@GET
	public Response enviaMensagem(@QueryParam("msg") String msg) {
		JMSProducer producer = ctx.createProducer();
		producer.setProperty("formato", "ebook");
		producer.send(topico, msg);
		return Response.ok().build();
	}

}
