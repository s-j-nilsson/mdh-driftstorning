package se.mdh.driftstorning.admin.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import se.mdh.driftstorning.admin.repository.AnledningRepository;
import se.mdh.driftstorning.admin.repository.KanalRepository;
import se.mdh.driftstorning.common.model.AnledningPost;
import se.mdh.driftstorning.common.model.KanalPost;

@Component
@Profile("init-mongo-data")
public class MongoDbInitializer implements CommandLineRunner {

  private KanalRepository kanalRepository;
  private AnledningRepository anledningRepository;

  public MongoDbInitializer(KanalRepository kanalRepository, AnledningRepository anledningRepository) {
    this.kanalRepository = kanalRepository;
    this.anledningRepository = anledningRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if(kanalRepository.findAll().isEmpty()) {
      KanalPost kanalPost1 = new KanalPost();
      kanalPost1.setNamn("ladok");
      kanalPost1.setText("Ladok");
      kanalRepository.save(kanalPost1);

      KanalPost kanalPost2 = new KanalPost();
      kanalPost2.setNamn("mdh");
      kanalPost2.setText("Mdh");
      kanalRepository.save(kanalPost2);

      KanalPost kanalPost3 = new KanalPost();
      kanalPost3.setNamn("selma");
      kanalPost3.setText("Selma");
      kanalRepository.save(kanalPost3);

      KanalPost kanalPost4 = new KanalPost();
      kanalPost4.setNamn("kronox");
      kanalPost4.setText("Kronox");
      kanalRepository.save(kanalPost4);
    }

    if(anledningRepository.findAll().isEmpty()) {
      AnledningPost anledningPost1 = new AnledningPost();
      anledningPost1.setNamn("uppgradering");
      anledningPost1.setText("Uppgradering");
      anledningPost1.setMeddelandeSv("Defaultmeddelande för uppgradering");
      anledningPost1.setMeddelandeEn("Default message for upgrade");
      anledningRepository.save(anledningPost1);

      AnledningPost anledningPost2 = new AnledningPost();
      anledningPost2.setNamn("backup");
      anledningPost2.setText("Backup");
      anledningPost2.setMeddelandeSv("Defaultmeddelande för backup");
      anledningPost2.setMeddelandeEn("Default message for backup");
      anledningRepository.save(anledningPost2);

      AnledningPost anledningPost3 = new AnledningPost();
      anledningPost3.setNamn("patchning");
      anledningPost3.setText("Patchning");
      anledningPost3.setMeddelandeSv("Defaultmeddelande för patchning");
      anledningPost3.setMeddelandeEn("Default message for patching");
      anledningRepository.save(anledningPost3);

      AnledningPost anledningPost4 = new AnledningPost();
      anledningPost4.setNamn("prestanda");
      anledningPost4.setText("Prestandaproblem");
      anledningPost4.setMeddelandeSv("Defaultmeddelande för prestandaproblem");
      anledningPost4.setMeddelandeEn("Default message for performance issues");
      anledningRepository.save(anledningPost4);
    }
  }
}
