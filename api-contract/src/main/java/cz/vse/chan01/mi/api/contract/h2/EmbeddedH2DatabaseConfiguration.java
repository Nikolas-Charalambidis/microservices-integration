package cz.vse.chan01.mi.api.contract.h2;


import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class EmbeddedH2DatabaseConfiguration {

	@Bean
	public DataSource dataSource() {
		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		return new EmbeddedDatabaseBuilder()
			.setName("contracts;DATABASE_TO_UPPER=false")
			.setType(EmbeddedDatabaseType.H2)
			.build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
