import { useState } from 'react';
import styled from 'styled-components';
import { SidebarMainButton } from '../../atoms/Buttons';
import SidebarTagsContainer from './SidebarTagsContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 150px;
  min-height: 30px;
  justify-content: flex-start;
`;

const Menu = ({ category, tags, onClick, selectedTab }) => {
  const [isClicked, setIsClicked] = useState(false);

  return (
    <Container>
      <SidebarMainButton onClick={() => setIsClicked(!isClicked)}>{category[1] || 'Category'}</SidebarMainButton>
      <SidebarTagsContainer isClicked={isClicked} tags={tags} onClick={onClick} selectedTab={selectedTab} />
    </Container>
  );
};

export default Menu;
