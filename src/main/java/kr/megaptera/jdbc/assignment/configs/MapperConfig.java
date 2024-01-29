package kr.megaptera.jdbc.assignment.configs;

import kr.megaptera.jdbc.assignment.mappers.CommentMapper;
import kr.megaptera.jdbc.assignment.mappers.PostMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public PostMapper postMapper() {
        return new PostMapper();
    }

    @Bean
    public CommentMapper commentMapper() {
        return new CommentMapper();
    }
}
