import simpleReducer from "./reducer";
import {applyMiddleware, combineReducers, createStore} from "redux";
import {composeWithDevTools} from "redux-devtools-extension";
import thunk from "redux-thunk";

const reducers = combineReducers({simpleReducer: simpleReducer})

const store = createStore(reducers, composeWithDevTools(applyMiddleware(thunk)))
export default store;