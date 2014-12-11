package com.tasks.boot;

import javax.jms.ConnectionFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.tasks.config.AppConfig;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// Load application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationContext.class);
		rootContext.setDisplayName("Spring Thymeleaf Tutorial");
		// Context loader listener
		servletContext.addListener(new ContextLoaderListener(rootContext));
		// Dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"dispatcher", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	
	 static String mailboxDestination = "mailbox-destination";

	    @Bean
	    Receiver receiver() {
	        return new Receiver();
	    }

	    @Bean
	    MessageListenerAdapter adapter(Receiver receiver) {
	        MessageListenerAdapter messageListener
	                = new MessageListenerAdapter(receiver);
	        messageListener.setDefaultListenerMethod("receiveMessage");
	        return messageListener;
	    }

	    @Bean
	    SimpleMessageListenerContainer container(MessageListenerAdapter messageListener,
	                                             ConnectionFactory connectionFactory) {
	        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	        container.setMessageListener(messageListener);
	        container.setConnectionFactory(connectionFactory);
	        container.setDestinationName(mailboxDestination);
	        
	        return container;
	    }

	    
	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(AppConfig.class);
		app.setLogStartupInfo(false);
		app.run(args);
		// on la lance
		// ConfigurableApplicationContext context = app.run(args);

		/*
		 * ProjectRepository pr=context.getBean(ProjectRepository.class);
		 * System.out.println("project : " + pr.findOne(1L)); TaskRepository
		 * tr=context.getBean(TaskRepository.class);
		 * System.out.println("task : " + tr.findOne(1L)); List<Task>
		 * tasks=(List<Task>) tr.findTasksByProjectId(1L);
		 * System.out.println("tasks : " + tasks.size());
		 * 
		 * Task t=new Task(); t.setName("syfadis");
		 * t.setProject(pr.findOne(1L)); t=tr.save(t);
		 * 
		 * tasks=(List<Task>) tr.findTasksByProjectId(1L);
		 * System.out.println("tasks : " + tasks.size());
		 * 
		 * User user=new User(); user.setEmail("hmam04@yahoo.fr");
		 * user.setFirstName("Said"); user.setLastName("EL HMAM");
		 * UserRepository ur=context.getBean(UserRepository.class);
		 * user=ur.save(user);
		 * 
		 * State st=new State(); st.setLabel("En cours"); StateRepository
		 * sr=context.getBean(StateRepository.class); st=sr.save(st);
		 * 
		 * UserTask ut=new UserTask(); ut.setComment("comment");
		 * ut.setState(st); ut.setTask(t); ut.setUser(user); UserTaskRepository
		 * utr=context.getBean(UserTaskRepository.class); ut=utr.save(ut);
		 * 
		 * utr.findOne(1L);
		 */
		// context.close();

	}
}
