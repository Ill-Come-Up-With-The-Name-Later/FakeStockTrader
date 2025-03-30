import utilities.Table;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Table<Double> table =
                Table.TableBuilder.<Double>build(1, 1).addColumn("Test").addRow();

        for(int i = 0; i < table.getRowCount(); i++) {
            for(int j = 0; j < table.getColCount(); j++) {
                table.addValueToRow(new Random().nextDouble(), i);
            }
        }

        table.print("%4.2f", 4);
        System.out.println(table.filter(x -> x > 0.6));
    }
}
