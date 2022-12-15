package ru.unclediga.example.openejb;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Any;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImdbGenerator implements NumberGenerator {
  private static Logger logger = Logger.getLogger(ImdbGenerator.class.getName());
  public String generateNumber() {
    String imdb = "tt-" + Math.abs(new Random().nextInt());
    logger.info("Generated IMDB id : " + imdb);
    return imdb;
  }
}