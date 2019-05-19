package repository;

import io.ebean.*;
import models.PassportCountry;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProfilePassportCountryRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;
    private final PassportCountryRepository passportCountryRepository;

    @Inject
    public ProfilePassportCountryRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
        this.passportCountryRepository = new PassportCountryRepository(ebeanConfig, executionContext);
    }

    /**
     * Inserts a passport into the profile passport country linking table
     * @param passport The passport to add
     * @return
     */
    public Optional<Integer> insertProfilePassportCountry(PassportCountry passport, Integer profileId) {
        Integer idOpt;
        try {
            idOpt = passportCountryRepository.getPassportCountryId(passport.getPassportName()).get();
        } catch(Exception e) {
           idOpt = null;
        }
        Integer passportId;
        if (idOpt == null) {
            passportId = passportCountryRepository.insert(passport).get();
        } else {
            passportId = idOpt;
        }
        Transaction txn = ebeanServer.beginTransaction();
        String qry = "INSERT into profile_passport_country (profile, passport_country) " +
                "VALUES (?, ?)";
        try {
            SqlUpdate query = Ebean.createSqlUpdate(qry);
            query.setParameter(1, profileId);
            query.setParameter(2, passportId);
            query.execute();
            txn.commit();
        } finally {
            txn.end();
        }
        return Optional.of(passportId);
    }
}
