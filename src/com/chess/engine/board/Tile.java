package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tile_coordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }


        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, Piece piece) {
        return piece != null ? new EmptyTile.OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }

    private Tile(final int tile_coordinate) {
        this.tile_coordinate = tile_coordinate;
    }


    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public int getTileCordinate(){
        return this.tile_coordinate;
    }
    public static final class EmptyTile extends Tile {

        EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public String toString() {
            return "-";
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }


        @Override
        public Piece getPiece() {
            return null;
        }

        public static final class OccupiedTile extends Tile {

            private final Piece pieceOnTile;

            private OccupiedTile(int coordinate, final Piece pieceOnTile) {
                super(coordinate);
                this.pieceOnTile = pieceOnTile;
            }

            @Override
            public String toString() {
                return getPiece().getPieceAllience().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
            }

            @Override
            public boolean isTileOccupied() {
                return true;
            }


            @Override
            public Piece getPiece() {
                return this.pieceOnTile;
            }


        }


    }

}
