package nick.pack.unit;

import nick.pack.utils.CommentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentDTOTest {
    @Test
    public void getDateTest() {
        CommentDTO commentDTO = new CommentDTO(1, "", "2023-09-19 15:48");
        Assertions.assertEquals("2023-09-19T15:48", commentDTO.getDate().toString());
    }
}
