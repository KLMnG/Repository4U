package Controls;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimePicker extends DatePicker {

    public DateTimePicker(){
        super();
        StringConverter<LocalDate> DateFormat = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                try {
                    LocalDate date = LocalDate.parse(string, dateFormatter);
                    return date;
                }catch (DateTimeParseException e) {
                    return null;
                }
            }
        };
        this.setConverter(DateFormat);
    }
}
