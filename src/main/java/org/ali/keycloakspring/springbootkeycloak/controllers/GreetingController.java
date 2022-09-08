package org.ali.keycloakspring.springbootkeycloak.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @GetMapping("/public/hello")
	public ResponseEntity<String> helloPublic() {
		return ResponseEntity.ok("Hello public user");
	}
	
	@GetMapping("/member/hello")
	public ResponseEntity<String> helloMember() {
		return ResponseEntity.ok("Hello dear member");
	}
	
	@GetMapping("/moderator/hello")
	public ResponseEntity<String> helloModerator() {
		return ResponseEntity.ok("Hello Moderator");
	}
	
	
	@GetMapping("/admin/hello")
	public ResponseEntity<String> helloAdmin() {
		return ResponseEntity.ok("Nice day, admin");
	}
}
