package org.grapheco;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**  
 * @date：2020年4月19日 上午11:38:55    
 * @author little-tiger
 * @version 1.0   
 * @since JDK 1.8  
 * @description：  
 */

@SpringBootApplication
@ServletComponentScan
public class LittleTigerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LittleTigerApplication.class,args);
	}

}
  
