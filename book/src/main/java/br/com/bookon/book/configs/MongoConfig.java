package br.com.bookon.book.configs;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@Configuration
@EnableMongoRepositories(value = { "br.com.bookon.book.repositories" })
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "bookon-book";
    }

    @Override
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createScramSha1Credential("book", "bookon-book", "book".toCharArray());
        return MongoClients.create(
                MongoClientSettings.builder()
                        .credential(credential)
                        .applyToClusterSettings(builder ->
                                builder.hosts(List.of(new ServerAddress("localhost", 27017))))
                        .build());
    }
}
