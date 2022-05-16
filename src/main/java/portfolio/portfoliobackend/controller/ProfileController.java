package portfolio.portfoliobackend.controller;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import portfolio.portfoliobackend.PortfolioBackendApplication;
import portfolio.portfoliobackend.model.PersonConnection;
import portfolio.portfoliobackend.model.Profile;
import portfolio.portfoliobackend.model.User;
import portfolio.portfoliobackend.service.MailService;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
public class ProfileController {

	@Autowired
	private MailService mailService;

	@RequestMapping("/get-profile/{id}")
	@Produces(APPLICATION_JSON)
	public Response getProfile(@PathVariable("id") String id) throws IOException {
		if(PortfolioBackendApplication.users.get(id) == null)
			return Response.status(404,"User with ID : "+id+" Not Found").build();
		Resource resource = new ClassPathResource("static/profiles/"+id+"/"+id+"-profile.json");
		InputStream inputStream = resource.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		return  Response.ok(mapper.readValue(inputStream,Profile.class)).build();
	}

	@PostMapping("/connectTo/{id}")
	public Response sendEmailToUser( @PathVariable("id") String id, @RequestBody PersonConnection person){
		User user = PortfolioBackendApplication.users.get("madhu-lakkoju");
		if(user!=null)
			mailService.sendEmail(user,person.getName()+" visited your Portfolio and wants to connect with you...",
					"Hi "+user.getName().replace("-"," ").toUpperCase()+"\n"+
							"You got a message from "+person.getName()+"\n\n"+
							person.getMessage() + "\n\n Connect at Mail: "+person.getEmail()+"\n"+
							" or Call At"+person.getPhone());
		return Response.accepted().build();
	}
	
}
