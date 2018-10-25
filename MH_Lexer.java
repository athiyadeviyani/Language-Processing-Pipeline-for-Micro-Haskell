
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
    // start = 0
    // accepting = 2, 3
    // dead = 4
    public String lexClass() { return ""; };
    public int numberOfStates() { return 5; };

    int next (int state, char c) {
        switch (state) {
            case 0: if (c == '-') return 1; else return 4;
            case 1: if (c == '-') return 2; else return 4;
            case 2: if (c == '-') return 2; else if (!CharTypes.isSymbolic(c) || !CharTypes.isNewline(c)) return 3; return 4;
            case 3: if (!CharTypes.isNewline(c)) return 3; return 4;
            default: return 4;
        }
    }

    boolean accepting (int state) {return (state == 2 || state == 3);}
    int dead() {return 4;}
}

static class TokAcceptor extends Acceptor implements DFA {

    String tok ;
    int tokLen ;
    TokAcceptor (String tok) {this.tok = tok ; tokLen = tok.length() ;}
    
    // add code here
    int totalStates = tokLen + 2;
    int garbageState = tokLen + 1;

    public String lexClass() { return tok; };
    public int numberOfStates() { return totalStates;};

    int next (int state, char c) {
        switch (state) {
            case 0: if (isLetter (c)) return 1; else return garbageState;
            default: garbageState;
        }
    }

    boolean accepting (int state) { return (state == 1); }
    int dead() {return garbageState;}

}

    // add definitions of MH_acceptors here

    static DFA varAcceptor = new VarAcceptor();
    static DFA numAcceptor = new NumAcceptor();
    static DFA booleanAcceptor = new BooleanAcceptor();
    static DFA symAcceptor = new SymAcceptor();
    static DFA whitespaceAcceptor = new WhitespaceAcceptor();
    static DFA commentAcceptor = new CommentAcceptor();
    static DFA tokAcceptor1 = new TokAcceptor("Integer");
    static DFA tokAcceptor2 = new TokAcceptor("Bool");
    static DFA tokAcceptor3 = new TokAcceptor("if");
    static DFA tokAcceptor4 = new TokAcceptor("then");
    static DFA tokAcceptor5 = new TokAcceptor("else");
    static DFA tokAcceptor6 = new TokAcceptor("(");
    static DFA tokAcceptor7 = new TokAcceptor(")");
    static DFA tokAcceptor8 = new TokAcceptor(";");

    new DFA[] {varAcceptor, numAcceptor, booleanAcceptor, symAcceptor, whitespaceAcceptor, commentAcceptor, tokAcceptor1, tokAcceptor2, tokAcceptor3, tokAcceptor4, tokAcceptor5, tokAcceptor6, tokAcceptor7, tokAcceptor8};
    MH_Lexer (Reader reader) {
	super(reader,MH_acceptors) ;
    }

}

