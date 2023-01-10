import { Reset } from 'styled-reset';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Signin from './components/templates/Signin';
import Navigator from './components/organisms/Navigator';
import PostList from './components/templates/PostList';
import Showcase from './components/templates/Showcase';

const App = () => {
  return (
    <>
      <Reset />
      <Navigator />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Showcase />} />
          <Route path="/signin" element={<Signin />} />
          <Route path="/posts" element={<PostList />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
