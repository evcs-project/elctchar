package com.elct.elctchar.temp;

import com.elct.elctchar.web.temp.TempEntity;
import com.elct.elctchar.web.temp.TempRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
public class TempEntityTest {

    @Autowired
    private TempRepository tempRepository;

    /**
     * 테스트 후에 이상없으면 완료
     */
    @Test
    @Transactional
    void insertTest()
    {
        tempRepository.save(new TempEntity("name", "password"));
        Optional<TempEntity> byId = tempRepository.findById(1L);
        
        Assertions.assertNotNull(byId);
    }
}
