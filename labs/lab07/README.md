# Further OOP — Lab 7

## Blocks Puzzle architecture and MVC

The final lab builds a complete Blocks game without hard-coding one board size
or exposing mutable model state.

### Architecture

- `BoardSpec` defines dimensions and completion regions.
- `Shape`, `Piece`, `PlacementPreview`, `MoveResult` and `BoardSnapshot` are
  value-oriented API types.
- `AbstractBlockModel` contains shared rules.
- `ModelSet` and `Model2dArray` use interchangeable storage strategies.
- `Palette` accepts a random generator so tests and replays are deterministic.
- `Controller` translates input into model commands; `GameView` renders
  snapshots and previews.

### Model contract

Complete `AbstractBlockModel`:

1. `preview` rejects any piece with an out-of-bounds, duplicate or occupied
   absolute cell. Legal previews report every region that would be completed.
2. `place` is atomic. Rejected moves change nothing. Accepted moves add all
   cells, determine all simultaneous completions, clear their union and apply
   the scoring policy once.
3. `canPlaceAnywhere` tries every relevant origin and has no side effects.
4. Snapshots and specification collections are immutable from the caller’s
   perspective.

The standard score is the number of placed cells plus ten points per region
completed in that move. Alternative `ScoringPolicy` implementations may be
used for extensions.

### UI and palette

Complete and polish drag/drop, legal ghost previews, completion highlighting,
palette replenishment, score display and game-over reporting. Rejected drops
return a sprite to the palette. Do not put pixel coordinates in the board
model.

### Testing

Both model implementations must produce equivalent externally visible results.
Write parameterized or shared contract tests and compare the models over a
sequence of moves. Consider negative coordinates, right/bottom overflow,
occupied cells, simultaneous row/column/sub-grid clears, rejected-move
atomicity and returned-collection mutation.

The supplied tests are smoke tests. Held-out assessment uses additional board
specifications and randomized equivalence traces.

### Extensions

Extensions should use the public API rather than modifying storage internals.
Examples include streak scoring, undo/redo using snapshots or commands,
alternative board specifications, replayable seeded games and automated play.

