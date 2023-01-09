import { Reset } from 'styled-reset';
import './App.css';
import Signin from './components/templates/Signin';
import Navigator from './components/organisms/Navigator';

const App = () => {
  return (
    <>
      <Reset />
      <Navigator />
      <Signin />
    </>
  );
};

export default App;
