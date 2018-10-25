
// File:   MH_Lexer.java

// Java template file for lexer component of Informatics 2A Assignment 1.
// Concerns lexical classes and lexer for the language MH (`Micro-Haskell').


import java.io.* ;

class MH_Lexer extends GenLexer implements LEX_TOKEN_STREAM {

static class VarAcceptor extends Acceptor implements DFA {
    // add code here
    // start = 0
    // accepting = 1
    // dead = 2
    public String lexClass() { return "VAR"; } ;
    public int numberOfStates() { return 3; } ;

    int next (int state, char c) {
        switch (state) {
            case 0: if (CharTypes.isSmall(c)) return 1; else return 2;
            case 1: if (CharTypes.isSmall(c) || CharTypes.isLarge(c) || CharTypes.isDigit(c) || c == '\'') return 1; else return 2;
            default: return 2;
        }
    }

    boolean accepting (int state) {return (state == 1); }
    int dead() {return 2; }
}

static class NumAcceptor extends Acceptor implements DFA {
    // add code here
    // start = 0
    // accepting = 1 and 2
    // dead = 3
    public String lexClass() { return "NUM"; } ;
    public int numberOfStates() { return 4; } ;

    int next (int state, char c) {
        switch (state) {
            case 0: if (c == '0') return 1; else if (CharTypes.isDigit(c) && (c != 0)) return 2;
            case 1: if (CharTypes.isDigit(c)) return 2; else return 3;
            default: return 3;
        }
    }

    boolean accepting (int state) {return (state == 1 || state == 2);}
    int dead() {return 3;}
}

static class BooleanAcceptor extends Acceptor implements DFA {
    // add code here
    // start = 0
    // accepting = 4
    // dead = 8
    public String lexClass() { return "BOOLEAN"; };
    public int numberOfStates() { return 9; };

    int next (int state, char c) {
        switch (state) {
            case 0: if (c == 'T') return 1; else if (c == 'F') return 5;
            case 1: if (c == 'r') return 2; return 8;
            case 2: if (c == 'u') return 3; return 8;
            case 3: if (c == 'e') return 4; return 8;
            case 4: if (c == 'a') return 6; return 8;
            case 5: if (c == 'l') return 7; return 8;
            case 6: if (c == 's') return 3; return 8;
            default: return 3;
        }
    }

    boolean accepting (int state) {return (state == 4);}
    int dead() {return 8;}
}

static class SymAcceptor extends Acceptor implements DFA {
    // add code here
    // start = 0
    // accepting = 1
    // dead = 2
    public String lexClass() { return "SYM"; };
    public int numberOfStates() { return 3; };

    int next (int state, char c) {
        switch (state) {
            case 0: if (CharTypes.isSymbolic(c)) return 1; else return 2;
            case 1: if (CharTypes.isSymbolic(c)) return 1; else return 2;
            default: return 2;
        }
    }

    boolean accepting (int state) {return (state == 1);}
    int dead() {return 2;}
}

static class WhitespaceAcceptor extends Acceptor implements DFA {
    // add code here
    // start = 0
    // accepting = 1
    // dead = 2
    public String lexClass() { return ""; };
    public int numberOfStates() { return 3; };

    int next (int state, char c) {
        switch (state) {
            case 0: if (CharTypes.isWhitespcae(c)) return 1; else return 2;
            case 1: if (CharTypes.isWhitespcae(c)) return 1; else return 2;
            default: return 2;
        }
    }

    boolean accepting (int state) {return (state == 1);}
    int dead() {return 2;}
}

static class CommentAcceptor extends Acceptor implements DFA {
    // add code here
}

static class TokAcceptor extends Acceptor implements DFA {

    String tok ;
    int tokLen ;
    TokAcceptor (String tok) {this.tok = tok ; tokLen = tok.length() ;}
    
    // add code here
}

    // add definitions of MH_acceptors here

    MH_Lexer (Reader reader) {
	super(reader,MH_acceptors) ;
    }

}

