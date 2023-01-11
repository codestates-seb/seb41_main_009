import { Reset } from 'styled-reset';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Signin from './components/templates/Signin';
import Navigator from './components/organisms/Navigator';
import PostList from './components/templates/PostList';
import Showcase from './components/templates/Showcase';
import Sidebar from './components/organisms/Sidebar';
import GlobalStyled from './GlobalStyle';

const App = () => {
  return (
    <>
      <Reset />
      <GlobalStyled />
      <Navigator />
      <Sidebar />
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
