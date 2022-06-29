package homework.config;


import homework.dao.RecipeRepositoryJdbi;
import homework.dao.UserRepositoryJdbi;
import homework.dao.impl.UserRepositoryMemoryJdbi;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@PropertySource("classpath:jdbc.properties")
@Configuration
public class JdbiConfig {
    @Bean
    public JdbiPlugin sqlObjectPlugin() {
        return new SqlObjectPlugin();
    }
    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbiPlugins.forEach(jdbi::installPlugin);
        rowMappers.forEach(jdbi::registerRowMapper);
        return  jdbi;
    }


    @Bean
    public RecipeRepositoryJdbi recipeRepositoryJdbi(Jdbi jdbi) {
        return jdbi.onDemand(RecipeRepositoryJdbi.class);
    }
}
