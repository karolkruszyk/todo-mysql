package pl.karolkruszyk;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Task {
    private int id;

    @NonNull
    private String sentence;
    @NonNull
    private String deadline;

    @Override
    public String toString() {
        return "ID: " + id + " ZADANIE: " + sentence + " DATA: " + deadline;
    }
}
