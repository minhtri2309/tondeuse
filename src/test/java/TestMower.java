import org.mower.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMower {

    final String FILEPATH = "src/test/resources";
    final String FILENAME = "sample.txt";
    final String SPACE_REGEX = "\\s+";

    MowerService mowerService;

    /**
     * Read sample file, getting grid size, mowers initial position and instructions.
     *
     * @throws IOException if file is not found
     */
    private void setupMowerService() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(FILEPATH, FILENAME));

        String[] lawnBounds = lines.getFirst().split(SPACE_REGEX);
        int xBound = Integer.parseInt(lawnBounds[0]);
        int yBound = Integer.parseInt(lawnBounds[1]);

        mowerService = new MowerService(xBound, yBound);

        Iterator<String> iterator = lines.iterator();
        iterator.next(); //skip lawn bounds

        while (iterator.hasNext()) {

            //get initial position for current mower
            String initialPositionLine = iterator.next();
            String[] initialPosition = initialPositionLine.split(SPACE_REGEX);
            Position initialPositionValue = new Position(initialPosition);

            //get instructions for current mower and add to mower service
            if (iterator.hasNext()) {
                List<Instruction> instructionList = parseInstructions(iterator.next());
                mowerService.addMower(initialPositionValue, instructionList);
            }
        }
    }

    @Test
    public void testMowers() throws IOException {
        Position expectedFirstPosition = new Position(1, 3, Orientation.North);
        Position expectedSecondPosition = new Position(5, 1, Orientation.East);

        setupMowerService();
        mowerService.start();

        Mower firstMower = mowerService.getMower(0);
        Mower secondMower = mowerService.getMower(1);

        checkPosition(expectedFirstPosition, firstMower.getCurrentPosition());
        checkPosition(expectedSecondPosition, secondMower.getCurrentPosition());
    }

    private List<Instruction> parseInstructions(String instructionsValue) {
        List<Instruction> instructionList = new ArrayList<>();
        for (char c : instructionsValue.toCharArray()) {
            Instruction instruction = Instruction.convertTo(c);
            instructionList.add(instruction);
        }
        return instructionList;
    }

    private void checkPosition(Position expectedPosition, Position mowerPosition) {
        assertEquals(expectedPosition.getX(), mowerPosition.getX(), "Mower X position is incorrect !");
        assertEquals(expectedPosition.getY(), mowerPosition.getY(), "Mower Y position is incorrect !");
        assertEquals(expectedPosition.getOrientation(), mowerPosition.getOrientation(), "Mower orientation is incorrect !");
    }
}
