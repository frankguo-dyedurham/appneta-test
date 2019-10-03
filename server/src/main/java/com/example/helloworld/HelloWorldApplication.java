package com.example.helloworld;

import com.example.helloworld.core.Player;
import com.example.helloworld.core.Template;
import com.example.helloworld.db.AdvancedDAO;
import com.example.helloworld.db.PlayerDAO;
import com.example.helloworld.errorhandling.AppExceptionMapper;
import com.example.helloworld.errorhandling.GenericExceptionMapper;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.resources.PlayerResource;
import com.example.helloworld.service.PlayerService;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.jersey.inject.hk2.Hk2RequestScope.Instance;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.hibernate.SessionFactory;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    private final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
        new HibernateBundle<HelloWorldConfiguration>(Player.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        final Template template = configuration.buildTemplate();

        environment.jersey().register(new HelloWorldResource(template));
        environment.jersey().register(PlayerResource.class);
        environment.jersey().register(GenericExceptionMapper.class);
        environment.jersey().register(AppExceptionMapper.class);

        environment.jersey().register(new AbstractBinder(){
            @Override
            protected void configure(){
                bind(hibernateBundle.getSessionFactory()).to(SessionFactory.class);
                bind(PlayerDAO.class).to(PlayerDAO.class);
                bind(PlayerService.class).to(PlayerService.class);
            }
        });
    }
}
