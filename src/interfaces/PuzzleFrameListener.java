package interfaces;

import java.io.File;

import util.TypeShuffle;

public interface PuzzleFrameListener {
	void onClick(File image, int size, TypeShuffle typeShuffle);
}
