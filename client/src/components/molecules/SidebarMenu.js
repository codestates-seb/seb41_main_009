import { useState } from 'react';
import styled from 'styled-components';
import { TextButton } from '../atoms/Buttons';
import SidebarTagsContainer from './SidebarTagsContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 150px;
  min-height: 30px;
  justify-content: flex-start;
`;

const Menu = ({ category, tags }) => {
  const [isClicked, setIsClicked] = useState(false);

  return (
    <Container>
      <TextButton onClick={() => setIsClicked(!isClicked)}>{category || 'Category'}</TextButton>
      <SidebarTagsContainer isClicked={isClicked} tags={tags} />
    </Container>
  );
};

export default Menu;
