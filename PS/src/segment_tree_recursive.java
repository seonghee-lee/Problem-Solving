import java.lang.Math;
import java.math.BigInteger;
import java.util.Arrays;

public class segment_tree_recursive {

    public static class SegmentTree {
        int level = 0;
        int length = 0;
        int[] inputList;
        int inputListLength;
        int inputStartIndex = 0;
        int inputEndIndex = 0;
        int treeIndex = 1;
        String calculationMethod;
        int[] resultList;

        public SegmentTree(int[] inputList, String calculationMethod) {
            this.calculationMethod = calculationMethod;
            this.inputListLength = inputList.length;
            this.inputEndIndex = this.inputListLength - 1;
            this.inputList = new int[this.inputListLength];

            for(int i = 0; i < this.inputListLength; i++) {
                this.inputList[i] = inputList[i];
            }

            this.level = (int) Math.ceil(Math.log(this.inputListLength) / Math.log(2)) + 1;
            this.length = (int) Math.pow(2, this.level);
            this.resultList = new int[this.length];

            this.make(0, this.inputListLength-1, 1);

        }

        public int gcd(int leftResult, int rightResult) {
            if (rightResult == 0) {
                return leftResult;
            }

            return this.gcd(rightResult, leftResult % rightResult);
        }

        public int method(int leftResult, int rightResult) {
            switch (this.calculationMethod) {
                case "sum":
                    return leftResult + rightResult;
                case "max":
                    return Math.max(leftResult, rightResult);
                case "gcd":
                    return this.gcd(leftResult, rightResult);
            }

            return leftResult + rightResult;
        }

        public int updateProcess(int inputStartIndex, int inputEndIndex, int treeIndex, int updateIndex, int updateValue) {
            
        	//구간에 영향을 미치지 않는 경우.
        	//그냥 리턴
        	if ((updateIndex < inputStartIndex) || (updateIndex > inputEndIndex)) {
                return this.resultList[treeIndex];
            }
        	
        	//업데이트하고자하는 리프 뤼치에 도달한 경우.
        	//그냥 바꿔
            if (inputStartIndex == inputEndIndex) {
                this.resultList[treeIndex] = updateValue;
                return this.resultList[treeIndex];
            }

            //둘 다 아니라면 left, right 탐색
            int inputMidIndex = (inputStartIndex + inputEndIndex) / 2;
            int leftResult = this.updateProcess(inputStartIndex, inputMidIndex, treeIndex * 2, updateIndex, updateValue);
            int rightResult = this.updateProcess(inputMidIndex + 1, inputEndIndex, treeIndex * 2 + 1, updateIndex, updateValue);

            this.resultList[treeIndex] = this.method(leftResult, rightResult);

            return this.resultList[treeIndex];
        }

        public void update(int updateIndex, int updateValue) {
            this.treeIndex = 1;
            this.inputList[updateIndex] = updateValue;

            this.updateProcess(this.inputStartIndex, this.inputEndIndex, this.treeIndex, updateIndex, updateValue);
        }

        public int getRangeProcess(int inputStartIndex, int inputEndIndex, int treeIndex, int rangeStartIndex, int rangeEndIndex) {
            //완전하게 벗어난 위치는 무효
        	if ((inputEndIndex < rangeStartIndex) || (inputStartIndex > rangeEndIndex)) {
                return 0;
            }

        	//구간에 완전히 들어감.(리프일수도, 아닐수도.)
        	//그냥 값 가져와 밑에 내려가지 마.
            if ((inputStartIndex >= rangeStartIndex) && (inputEndIndex <= rangeEndIndex)) {
                return this.resultList[treeIndex];
            }

            //위에서 걸리지 않았으면 왼, 오 탐색(완전히 겹치지도, 안겹치지도 않은 그런 경우니까. 애매한 경우니 왼쪽 오른쪽 한번씩 가보자~)
            int inputMidIndex = (inputStartIndex + inputEndIndex) / 2;

            int leftResult = this.getRangeProcess(inputStartIndex, inputMidIndex, treeIndex * 2, rangeStartIndex, rangeEndIndex);
            int rightResult = this.getRangeProcess(inputMidIndex + 1, inputEndIndex, treeIndex * 2 + 1, rangeStartIndex, rangeEndIndex);

            return this.method(leftResult, rightResult);
        }

        public int getRange(int rangeStartIndex, int rangeEndIndex) {
            return this.getRangeProcess(this.inputStartIndex, this.inputEndIndex, this.treeIndex, rangeStartIndex, rangeEndIndex);
        }

        public int make(int inputStartIndex, int inputEndIndex, int treeIndex) {
            //1. 리프노드라면 ti에 현재 값을 채우고 값을 가지고 올라온다.
        	if (inputStartIndex == inputEndIndex) {
                this.resultList[treeIndex] = this.inputList[inputStartIndex];
                return this.resultList[treeIndex];
            }
        	//2. 다음 왼/오를 구분하기 위해 중간값을 찾는다.
            int inputMidIndex = (inputStartIndex + inputEndIndex) / 2;
            //3. 왼쪽값과 오른쪽값을 가져온다.
            int left_result = this.make(inputStartIndex, inputMidIndex, treeIndex * 2);
            int right_result = this.make(inputMidIndex + 1, inputEndIndex, treeIndex * 2 + 1);
            //4. 두 값의 연산 결과를 현 위치에 저장하고 , 해당 값을 리턴
            this.resultList[treeIndex] = this.method(left_result, right_result);
            return this.resultList[treeIndex];
        }

    }

    public static void main(String[] args) {
        int[] numberList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        SegmentTree segmentTree = new SegmentTree(numberList, "sum");
        System.out.println(Arrays.toString(segmentTree.resultList));
        System.out.println(segmentTree.getRange(3, 5));
        segmentTree.update(4, 7);
        System.out.println(Arrays.toString(segmentTree.resultList));
        System.out.println(segmentTree.getRange(3, 5));
    }
}