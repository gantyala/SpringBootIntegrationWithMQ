package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.configuration.HelloWorldAmqConfig;

import junit.framework.TestCase;

import javax.jms.Connection;

import javax.jms.ConnectionFactory;

import javax.jms.DeliveryMode;

import javax.jms.Destination;

import javax.jms.JMSException;

import javax.jms.MessageProducer;

import javax.jms.Session;

import javax.jms.TextMessage;

 

import org.junit.After;

import org.junit.Before;

import org.junit.Rule;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.rule.OutputCapture;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootIntegrationWithMqApplicationTests extends TestCase {
	    @Rule
		
		    public OutputCapture outputCapture = new OutputCapture();
		    @Autowired
		
		    @Qualifier("jmsConnectionFactory")
		
		    ConnectionFactory jmsConnectionFactory;
		
		    String queueName = HelloWorldAmqConfig.HELLO_WORLD_QUEUE;
		
		    MessageProducer jmsamqproducer;
		
		    Destination jmsamqdestination;
		
		    Session jmsamqsession;
		
		    Connection jmsamqconn;
		
		    @Before
		
		    public void setUpJmsSession() throws JMSException {
		      jmsamqconn = jmsConnectionFactory.createConnection();
		
		        jmsamqconn.start();
		
		        jmsamqsession = jmsamqconn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		        jmsamqdestination = jmsamqsession.createQueue(queueName);
		
		        jmsamqproducer = jmsamqsession.createProducer(jmsamqdestination);
		
		        jmsamqproducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		    }
		
		     
		
		    @After
		
		    public void tearDownJmsSession() throws JMSException {
		
		        jmsamqsession.close();
		
		        jmsamqconn.close();
		
		         
		
		    }
		
		 
		
		    @Test
		
		  public void testSendMsgToConsumer() {
		
		        try {
		
		            TextMessage msg = jmsamqsession.createTextMessage("This is message from producer");
		
		            jmsamqproducer.send(jmsamqdestination, msg);
		
		            Thread.sleep(3000L);
		
		            assertTrue(this.outputCapture.toString().contains("This is message from producer"));
		
		        } catch (JMSException e) {
		
		            fail();
		
		        } catch (InterruptedException e) {
		
		            fail();
		
		        }
		
		 
		
		    }
		
		 
		
		

	@Test
	public void contextLoads() {
	}

}
