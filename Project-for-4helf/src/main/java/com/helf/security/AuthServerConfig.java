package com.helf.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


////
////
////
////
////import com.helf.config.Configurations;
////import com.helf.service.CustomTokenEnricher;
////import com.helf.service.CustomUserDetailService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.authentication.AuthenticationManager;
////
////
////import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
////import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
////import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
////import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
////import org.springframework.security.oauth2.provider.token.TokenStore;
////import org.springframework.stereotype.Service;
////
////@EnableAuthorizationServer
////@Service
////public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
////
////    @Autowired
////    TokenStore tokenStore;
////
////    @Autowired
////    private AuthenticationManager authenticationManager;
////
////    @Autowired
////    private CustomTokenEnricher customTokenEnricher;
////
////    @Autowired
////    CustomUserDetailService userDetailsService;
////
////    @Autowired
////    private Configurations configuration;
////
////
////
////    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
////        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager).
////                tokenEnhancer(customTokenEnricher);
////    }
////
////    @Override
////    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
////
////        security.tokenKeyAccess("permitAll()").checkTokenAccess("hasRole('TRUSTED_CLIENT')")
////                .allowFormAuthenticationForClients();
////
////    }
////
////
//////    @Override
//////    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//////        clients.inMemory().withClient(configuration.getClientId()).secret("{noop}"+configuration.getSecret()).authorizedGrantTypes(configuration.getGrantType().split(","))
//////                .scopes(configuration.getScope().split(",")).redirectUris(configuration.getRedirectUri()).authorities(configuration.getAuthroties())
//////                .accessTokenValiditySeconds(Integer.parseInt(configuration.getAccessTokenValidityInSeconds())).autoApprove(true);
//////    }
////}
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
@Configuration
@EnableWebSecurity
public class AuthServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/ignore").permitAll()  // Exclude signup endpoint from authentication
                .anyRequest().permitAll()  // Allow access to all other requests without authentication
                .and()
                .csrf().disable();  // Disable CSRF protection for simplicity (not recommended for production)
    }
}

//
//
//
//
//
//
