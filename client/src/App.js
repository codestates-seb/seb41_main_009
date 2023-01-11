import { Reset } from 'styled-reset';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Signin from './components/templates/Signin';
import Navigator from './components/organisms/Navigator';
import PostList from './components/templates/PostList';
import Showcase from './components/templates/Showcase';
import Sidebar from './components/organisms/Sidebar';
import SeriesList from './components/templates/SeriesList';
import PostPage from './components/templates/PostPage';
import PostCreatePage from './components/templates/PostCreatePage';
import GlobalStyled from './GlobalStyle';
import PublicRoute from './routes/PublicRoute';

const App = () => {
  return (
    <>
      <Reset />
      <GlobalStyled />
      <Navigator />
      <Sidebar />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<PublicRoute component={<Showcase />} />} />
          <Route path="/signin" element={<PublicRoute component={<Signin />} />} />
          <Route path="/posts" element={<PublicRoute component={<PostList />} />} />
          <Route path="/posts/:id" element={<PublicRoute component={<PostPage />} />} />
          <Route path="/posts/new" element={<PublicRoute component={<PostCreatePage />} />} />
          <Route path="/series" element={<PublicRoute component={<SeriesList />} />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default App;
