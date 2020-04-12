package order;

import static order.SecurityConstants.LOGIN_URL;

import static order.SecurityConstants.SIGN_UP_URL;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	@Autowired
	public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
				.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll().antMatchers(HttpMethod.GET, LOGIN_URL).permitAll()
				.antMatchers(HttpMethod.GET, "/oauth/token", "/states*", "/states/*", "/country*").permitAll()
				.antMatchers(HttpMethod.POST, "/oauth/token").permitAll().antMatchers(HttpMethod.GET, "/problems/*")
				.permitAll().antMatchers(HttpMethod.GET, "/country/*").permitAll()
//				.antMatchers(HttpMethod.GET, "/identifier*").permitAll().antMatchers(HttpMethod.GET, "/identifier/*")
//				.permitAll()
				.antMatchers(HttpMethod.GET, "/locations*").permitAll()
				.antMatchers(HttpMethod.POST, "/kafka/publish*").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-ui.html*").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
				.antMatchers(HttpMethod.GET, "/v2/api*").permitAll()
				.antMatchers(HttpMethod.GET, "/webjars/springfox*/**").permitAll()
				.antMatchers(HttpMethod.POST, "/blockuser").permitAll()
				.antMatchers(HttpMethod.GET, "/userdetails/*").permitAll()
				.antMatchers(HttpMethod.GET, "/locations/*").permitAll()
				.antMatchers(HttpMethod.GET, "/orders/*").permitAll()
				.antMatchers(HttpMethod.POST, "/createorder*").permitAll()
				.antMatchers(HttpMethod.POST, "/forgotpassword*").permitAll()
				.antMatchers(HttpMethod.GET, "/checkusernameexistance/*").permitAll()
				.antMatchers(HttpMethod.GET, "/updatepassword/*").permitAll().antMatchers(HttpMethod.GET, "/")
				.permitAll().antMatchers(HttpMethod.POST, "/verifyaccount*").permitAll()
				.antMatchers(HttpMethod.POST, "/userupadteoth*").permitAll().antMatchers(HttpMethod.GET, "/login*")
				.permitAll().antMatchers(HttpMethod.POST, "/getverification*").permitAll().anyRequest().authenticated()
				.and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
}
