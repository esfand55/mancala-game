package com.dzone.mancala.game.repository;

import com.dzone.mancala.game.model.KalahaGame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class KalahaGameRepositoryTests {

    @Autowired
    KalahaGameRepository gameRepository;

    @Test
    public void testRepository() throws  Exception {
        this.gameRepository.deleteAll();

        KalahaGame saved= this.gameRepository.save(new KalahaGame(6));

        Assert.assertNotNull(saved.getId());

        List<KalahaGame> list  = this.gameRepository.findAll();

        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testLoadingKalahaInstances() {

    }
}
