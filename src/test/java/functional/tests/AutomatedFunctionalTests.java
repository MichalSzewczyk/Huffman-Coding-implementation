package functional.tests;

import com.huffman.coding.logic.*;
import com.huffman.coding.model.ValueWithCode;
import com.huffman.coding.model.ValueWithFrequency;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.AssertJUnit.assertEquals;


public class AutomatedFunctionalTests {
    @Test(dataProvider = "testDataProvider")
    void verifyThatResultsWithPassedResultForPassedDataSet(
            List<ValueWithFrequency<Character>> valueWithFrequencyList,
            List<ValueWithCode<Character>> correctResultList
    ) {
        //GIVEN
        Coding<Character> objectCoding = new CodingFacade<>();
        valueWithFrequencyList.forEach(objectCoding::add);

        //WHEN
        List<ValueWithCode<Character>> resultList = objectCoding.getCode();

        //THEN
        resultList.sort(getComparator());
        assertEquals(resultList, correctResultList);
    }

    private Comparator<ValueWithCode<Character>> getComparator() {
        return Comparator.comparing(ValueWithCode::getValue);
    }

    @DataProvider
    private Object[][] testDataProvider() {
        return new Object[][]{
                {Collections.emptyList(), Collections.emptyList()},

                {List.of(new ValueWithFrequency<>('a', 5),
                        new ValueWithFrequency<>('b', 9),
                        new ValueWithFrequency<>('c', 12),
                        new ValueWithFrequency<>('d', 13),
                        new ValueWithFrequency<>('e', 16),
                        new ValueWithFrequency<>('f', 45)
                ),
                        List.of(new ValueWithCode<>('a', "1100"),
                                new ValueWithCode<>('b', "1101"),
                                new ValueWithCode<>('c', "100"),
                                new ValueWithCode<>('d', "101"),
                                new ValueWithCode<>('e', "111"),
                                new ValueWithCode<>('f', "0")
                        )}
        };
    }
}
