public class DiamondApp {
    public static void main(String[] args) {
        printDiamond(6, 3);
    }

    private static void printDiamond(int height, int width) {
        if (height >=1 && height <= 80 && width >= 1 && width <= 80) {
            StringBuilder stringBuilder = new StringBuilder();
            int lineNumber = 0;
            int position;
            for (int i = 0; i < height * width; i++) {
                position = i - (width * lineNumber);
                if (position == width/2 - (Math.min(lineNumber, height - 1 - lineNumber)) * (width/height)
                        || position == width/2 + (Math.min(lineNumber, height - 1 - lineNumber)) * (width/height)) {
                    stringBuilder.append("#");
                } else {
                    stringBuilder.append(" ");
                }
                if (position == width - 1) {
                    stringBuilder.append("\n");
                }
                    if ((i + 1) % width == 0) lineNumber ++;
            }
            System.out.println(stringBuilder);
        } else {
            System.out.println("Неверная высота и/или ширина");
        }
    }
}
