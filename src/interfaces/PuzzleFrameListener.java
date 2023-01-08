package interfaces;

import model.Puzzle;

public interface PuzzleFrameListener {
	void onClick(Puzzle puzzle, long currentTime, boolean isNewGame);
}
