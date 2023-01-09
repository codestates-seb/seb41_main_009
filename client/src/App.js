import { Reset } from 'styled-reset';
import './App.css';
import Singin from './components/templates/Signin';
import Navigator from './components/organisms/Navigator';

const App = () => {
  return (
    <>
      <Reset />
      <Navigator />
      <Singin />
    </>
  );
};

export default App;
