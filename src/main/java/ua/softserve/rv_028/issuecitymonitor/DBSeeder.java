package ua.softserve.rv_028.issuecitymonitor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.softserve.rv_028.issuecitymonitor.entity.*;
import ua.softserve.rv_028.issuecitymonitor.entity.enums.*;

import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.Random;

@Component
public class DBSeeder {

    private SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger(DBSeeder.class.getName());

    @Autowired
    public DBSeeder(EntityManagerFactory factory){

        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        sessionFactory = factory.unwrap(SessionFactory.class);
        try {
            logger.info("Seeding database...");
            fillDatabase();
            logger.info("Seeding finished");
        } catch (RuntimeException e) {
            logger.error("Seeding has been done already. Skipping...");
        }
    }

    private void fillDatabase() throws RuntimeException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        for(int i=0; i < 10 ; i++){
            User user = new User();
            user.setFirstName("Tom"+i);
            user.setLastName("Jerry"+i);
            user.setEmail("tom"+i+"@mail.rv.ua");
            user.setPhone("+380997755331");
            user.setPassword(i+""+i+""+i);
            user.setUserRole(randomEnum(UserRole.class));
            user.setRegistrationDate(randomDate());
            user.setAvatarUrl("http://url.com"+i);
            user.setUserAgreement(r.nextBoolean());
            user.setUserStatus(randomEnum(UserStatus.class));
            session.save(user);

            for(int a=0; a < 3 ; a++) {
                Event event = new Event();
                event.setTitle("Title" +a+""+i);
                event.setCategory(randomEnum(EventCategory.class));
                event.setDescription("description" +a+""+ i);
                event.setInitialDate(randomDate());
                event.setUser(user);
                event.setLatitude(r.nextDouble());
                event.setLongitude(r.nextDouble());
                session.save(event);

                Petition petition = new Petition();
                petition.setCategory(randomEnum(PetitionCategory.class));
                petition.setDescription("descript" +a+""+ i);
                petition.setInitialDate(randomDate());
                petition.setTitle("Title" +a+""+ i);
                petition.setUser(user);
                session.save(petition);

                Issue issue = new Issue();
                issue.setCategory(randomEnum(IssueCategory.class));
                issue.setDescription("desc" +a+""+ i);
                issue.setInitialDate(randomDate());
                issue.setLatitude(r.nextDouble());
                issue.setLongitude(r.nextDouble());
                issue.setTitle("title" +a+""+ i);
                issue.setUser(user);
                session.save(issue);

                for (int j = 0; j < 5; j++) {
                    EventAttachment eventAttachment = new EventAttachment();
                    eventAttachment.setAttachmentUrl("url" +j+""+a+""+ i);
                    eventAttachment.setEvent(event);
                    eventAttachment.setUser(user);
                    session.save(eventAttachment);

                    EventChangeRecord eventChangeRecord = new EventChangeRecord();
                    eventChangeRecord.setChangeRecordStatus(randomEnum(ChangeRecordStatus.class));
                    eventChangeRecord.setEvent(event);
                    eventChangeRecord.setMessage("msg" +j+""+a+""+ i);
                    eventChangeRecord.setUser(user);
                    session.save(eventChangeRecord);

                    IssueAttachment issueAttachment = new IssueAttachment();
                    issueAttachment.setAttachmentUrl("url" +j+""+a+""+ i);
                    issueAttachment.setIssue(issue);
                    issueAttachment.setUser(user);
                    session.save(issueAttachment);

                    IssueChangeRecord issueChangeRecord = new IssueChangeRecord();
                    issueChangeRecord.setChangeRecordStatus(randomEnum(ChangeRecordStatus.class));
                    issueChangeRecord.setIssue(issue);
                    issueChangeRecord.setMessage("msg" +j+""+a+""+ i);
                    issueChangeRecord.setUser(user);
                    session.save(issueChangeRecord);

                    PetitionAttachment petitionAttachment = new PetitionAttachment();
                    petitionAttachment.setAttachmentUrl("url" +j+""+a+""+ i);
                    petitionAttachment.setPetition(petition);
                    petitionAttachment.setUser(user);
                    session.save(petitionAttachment);

                    PetitionChangeRecord petitionChangeRecord = new PetitionChangeRecord();
                    petitionChangeRecord.setChangeRecordStatus(randomEnum(ChangeRecordStatus.class));
                    petitionChangeRecord.setPetition(petition);
                    petitionChangeRecord.setMessage("msg" +j+""+a+""+ i);
                    petitionChangeRecord.setUser(user);
                    session.save(petitionChangeRecord);

                    UserVote userVote = new UserVote();
                    userVote.setPetition(petition);
                    userVote.setUser(user);
                    session.save(userVote);
                }
            }
        }
        transaction.commit();
    }

    private static final Random r = new Random();

    private String randomDate(){
        return new Date(r.nextLong()).toString();
    }
    private <T extends Enum<?>> T randomEnum(Class<T> classname){
        int x = r.nextInt(classname.getEnumConstants().length);
        return classname.getEnumConstants()[x];
    }
}
