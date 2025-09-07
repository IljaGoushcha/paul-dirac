# paul-dirac

# Security
## To add security for actuator endpoints
These will protect your API and Actuator endpoints. This can be further fine-tuned using:

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic();
        return http.build();
    }
}

Use the following environment variables
ACTUATOR_NAME=something1;ACTUATOR_SYKRET=something2

# Building package and run
## With Maven
In terminal
export ACTUATOR_NAME=something1
export ACTUATOR_SYKRET=something2
note here something1 and something2 do not really matter, since you will be passing those values to the .jar file explicitly when running it. 

mvn clean package
and then run with a command
ACTUATOR_NAME=something1 ACTUATOR_SYKRET=something2 java -jar ./target/pauldirac-0.0.1-SNAPSHOT.jar

## With Maven wrapper (mvnw)
./mvnw package
ACTUATOR_NAME=something1 ACTUATOR_SYKRET=something2 java -jar ./target/pauldirac-0.0.1-SNAPSHOT.jar

