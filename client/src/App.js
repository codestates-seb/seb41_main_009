import { Reset } from 'styled-reset';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Signin from './components/templates/Signin';
import Navigator from './components/organisms/Navigator';
import PostList from './components/templates/PostList';
import Sidebar from './components/organisms/Sidebar';

const App = () => {
  return (
    <>
      <Reset />
      <Navigator />
      <Sidebar />
      <BrowserRouter>
        <Routes>
          <Route path="/signin" element={<Signin />} />
          <Route path="/posts" element={<PostList />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
