package uz.com.RESTAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.com.RESTAPI.model.Card;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRpository extends JpaRepository<Card,Integer> {

    Optional<Card> findByCardIdAndDeletedAtIsNull(Integer cardId);

    Boolean existsByCardIdAndDeletedAtIsNull(Integer cardId);

    Boolean existsByCardNumberAndDeletedAtIsNull(String cardNumber);

    List<Card> findAllByUserIdAndDeletedAtIsNull(Integer userId);

    List<Card> findAllByDeletedAtIsNullOrderByCardId();
}
