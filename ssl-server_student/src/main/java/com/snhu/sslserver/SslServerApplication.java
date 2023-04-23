package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{
    @RequestMapping("/hash")
    public String myHash()throws NoSuchAlgorithmException {
    	String data = "Ronald W. Sudol III";
     
    
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.reset();
    byte[] hashbytes = md.digest(data.getBytes(StandardCharsets.UTF_8));
    String hexed = bytesToHex(hashbytes);
    
    
        return "Data: "+data+"     SHA-256: "+hexed;
    }

    //bytesToHash was not predefined
    //pulled this method by Mirza Q Ali
    //from: https://stackoverflow.com/questions/7647692/java-messagedigest-doesnt-work
    private static String bytesToHex(byte[] b) {
                char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                        'a', 'b', 'c', 'd', 'e', 'f','A', 'B', 'C', 'D', 'E', 'F' };
                StringBuffer buf = new StringBuffer();
                for (int j = 0; j < b.length; j++) {
                    buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
                    buf.append(hexDigit[b[j] & 0x0f]);
                }
                return buf.toString();
            }
}