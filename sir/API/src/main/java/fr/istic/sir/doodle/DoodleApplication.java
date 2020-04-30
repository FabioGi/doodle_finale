package fr.istic.sir.doodle;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.istic.sir.doodle.service.interfaces.IdoodleService;

@SpringBootApplication
public class DoodleApplication implements CommandLineRunner {
	@Autowired
	private IdoodleService doodleS;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DoodleApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		JAXBContext jaxbContext;
		String xmlString = "<employee> <department>  <id>101</id>"
				+ "   <name>IT</name> </department> <firstName>Lokesh</firstName> "
				+ " <id>1</id>  "
				+ "<lastName>Gupta</lastName>"
				+ "  </employee>";   
	               
	           
		try
		{
		    jaxbContext = JAXBContext.newInstance(Employee.class);              
		 
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		 
		    Employee employee = (Employee) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		     
		    System.out.println(employee);
		}
		catch (JAXBException e) 
		{
		    e.printStackTrace();
		}
		// doodleS.createUserTest();
//		doodleS.createSondage();
//		doodleS.createCrenaux();
//		doodleS.validedSondage(); 
//		doodleS. choseDate();
//		doodleS.selectCrenauxforMeeting();
		// System.out.println(xmlString);
	}
	

}
