/**
 * @author Vladislav Korobko
 */
public class DiamondApp {
    public static void main(String[] args) {
        try {
            printDiamond(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат. Запуск приложение осуществляется командой \"java -jar <название приложения> " +
                    "<высота ромба(число от 1 до 80)> <ширина ромба(число от 1 до 80)>\"");
        }
    }

    /**
     * Распечатать ромб
     * @param height высота
     * @param width ширина
     */
    private static void printDiamond(int height, int width) {
        if (height >=1 && height <= 80 && width >= 1 && width <= 80) {
            StringBuilder stringBuilder = new StringBuilder();
            //текущий номер строки
            int lineNumber = 0;
            //текущая позиция в строке
            int position;
            for (int i = 0; i < height * width; i++) {
                //позиция равна разнице индекса и произведения ширины на номер строки (для соблюдения условия с одним циклом)
                position = i - (width * lineNumber);

                if (isLattice(width, height, position, lineNumber)) {
                    stringBuilder.append("#");
                } else {
                    stringBuilder.append(" ");
                }
                //если текущий символ в конце строки, добавляется перенос
                if (position == width - 1) {
                    stringBuilder.append("\n");
                    lineNumber ++;
                }
            }
            System.out.println(stringBuilder);
        } else {
            System.out.println("Неверная высота и/или ширина");
        }
    }

    /**
     * Метод проверяет, соответствует ли данная позиция символу решётки (границе ромба)
     * @param width ширина ромба
     * @param height высота ромба
     * @param position позиция текущего символа в строке
     * @param lineNumber текущий номер строки
     * @return {@code true} если позиция соответствует символу решётки, иначе {@code false}
     */
    private static boolean isLattice(int width, int height, int position, int lineNumber) {
        if ((lineNumber == 0 || lineNumber == height - 1) && position == width / 2) {
            return true;
        }
        //если строка расположена в центре (в случае с чётной высотой таких строки - две), решётки располагаются по краям
        boolean isCentralLine = false;
        if ((height % 2 != 0 && lineNumber == height / 2)
        || (height % 2 == 0 && (lineNumber == height/2 || lineNumber == height/2 - 1))) {
            isCentralLine = true;
        }

        if (lineNumber > 0 && lineNumber < height - 1) {

            /* Отклонение от центра для каждой строки, не являющейся первой и последней.
            В случае с чётной шириной ромб "обрезается" по правому краю.
            Для центральных строк решётки автоматически располагаются по краям.
            Для крайних - по центру, или, в случае с чётной шириной, на правой из двух центральных позиции */
            double offset = Math.min((Math.min(lineNumber, height - 1 - lineNumber)) * Math.ceil(Double.valueOf(width) / height), width/2);


            if ((!isCentralLine && (position == width/2 - offset || position == width/2 + offset))
            || (isCentralLine && (position == width - 1 || position == 0))
            || (width/2 + offset == width && position == width - 1)) {
                return true;
            }
        }

        return false;
    }
}
