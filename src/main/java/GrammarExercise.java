import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GrammarExercise {
//    private static Object RuntimeException;

    public static void main(String[] args) {
        //需要从命令行读入
        Scanner scanner = new Scanner(System.in);

        String firstWordList = scanner.next();
        String secondWordList = scanner.next();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行

    }

    public static boolean haveRightInput(String firstWordList, String secondWordList) {
        ArrayList<String> firstList = new ArrayList<>(Arrays.asList(firstWordList.split(",")));
        ArrayList<String> secondList = new ArrayList<>(Arrays.asList(secondWordList.split(",")));

        boolean firstBoolean = inputHasDoubleCommas(firstList, secondList);
        boolean secondBoolean = inputHasInvalidSymbol(firstList, secondList);

        return !firstBoolean && !secondBoolean;
    }
    public static boolean inputHasDoubleCommas(ArrayList<String> firstList, ArrayList<String> secondList) {
        return (firstList.contains("") || secondList.contains(""));
    }

    public static boolean inputHasInvalidSymbol(ArrayList<String> firstList, ArrayList<String> secondList) {
        boolean containInvalidSymbol = true;

        String firstString = String.join("", firstList);
        int[] firstIntList = firstString.codePoints().toArray();

        int[] firstIntListFilter = Arrays.stream(firstIntList).filter(aInt -> {
            return aInt < 65 || aInt > 122 || (aInt > 90 && aInt < 97);
        }).toArray();

        String secondString = String.join("", secondList);
        int[] secondIntList = secondString.codePoints().toArray();

        int[] secondIntListFilter = Arrays.stream(secondIntList).filter(aInt -> {
            return aInt < 65 || aInt > 122 || (aInt > 90 && aInt < 97);
        }).toArray();

        if (firstIntListFilter.length == 0 && secondIntListFilter.length == 0) {
            containInvalidSymbol = false;
        }

        return containInvalidSymbol;
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        if (!haveRightInput(firstWordList, secondWordList)) {
            throw new RuntimeException();
        }

        ArrayList<String> firstList = new ArrayList<>(Arrays.asList(firstWordList.split(",")));
        ArrayList<String> secondList = new ArrayList<>(Arrays.asList(secondWordList.split(",")));

        Stream<String> upperCaseFirstList = firstList.stream().map(String::toUpperCase);
        List<String> upperCaseSecondList = secondList.stream().map(String::toUpperCase).collect(Collectors.toList());
        ArrayList<String> upperCaseSecondArrayList = new ArrayList<>(upperCaseSecondList);

        List<String> listAfterFilter = upperCaseFirstList.filter(upperCaseSecondArrayList::contains).distinct().collect(Collectors.toList());

        return formatTransform(listAfterFilter);
    }//        List<String> listAfterFilter = firstList.stream().filter(secondList::contains).distinct().collect(Collectors.toList());


    public static List<String> formatTransform(List<String> list) {
        return list.stream().map(word -> {
            return String.join(" ", word.toUpperCase().split(""));
        }).collect(Collectors.toList());
    }

}
