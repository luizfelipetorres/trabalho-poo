package interfaces;

import model.Puzzle;
import util.TypeShuffle;

public interface PuzzleFrameListener {
	void onClick(Puzzle puzzle, long currentTime);
}
